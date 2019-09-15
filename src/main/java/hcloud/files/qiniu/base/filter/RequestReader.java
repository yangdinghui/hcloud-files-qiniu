package hcloud.files.qiniu.base.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.*;
import java.nio.charset.Charset;

/**
 * description 描述这个类的主要功能、用途
 * 创建时间 2019/9/8
 *
 * @author 杨丁辉
 */
public class RequestReader extends HttpServletRequestWrapper {

    private static final Logger log = LoggerFactory.getLogger(RequestReader.class);

    private final byte[] body;

    public RequestReader(HttpServletRequest request) throws IOException {
        super(request);
        String sessionStream = getBodyString(request);
        this.body = sessionStream.getBytes(Charset.forName("UTF-8"));
    }


    public ServletInputStream getInputStream() throws IOException {
        final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(this.body);
        return new ServletInputStream() {
            public int read() throws IOException {
                return byteArrayInputStream.read();
            }


            public boolean isFinished() {
                return false;
            }


            public boolean isReady() {
                return false;
            }


            public void setReadListener(ReadListener listener) {
            }
        };
    }


    public BufferedReader getReader() throws IOException {
        return new BufferedReader(new InputStreamReader(getInputStream()));
    }


    public InputStream cloneInputStream(ServletInputStream inputStream) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = 0;
        try {
            while ((len = inputStream.read(buffer)) > -1) {
                byteArrayOutputStream.write(buffer, 0, len);
            }
            byteArrayOutputStream.flush();
        } catch (IOException e) {
            log.info("clone servletInputStream failed", e);
            throw e;
        }
        return new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
    }


    public String getBodyString(ServletRequest request) {
        StringBuilder sb = new StringBuilder();
        InputStream inputStream = null;
        BufferedReader reader = null;
        try {
            inputStream = cloneInputStream(request.getInputStream());
            reader = new BufferedReader(new InputStreamReader(inputStream, Charset.forName("UTF-8")));
            String line = "";
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            log.error("get request body code error:", e);
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return sb.toString();
    }
}
