package com.xsc.lottery.web.security.jcaptcha;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.octo.captcha.service.CaptchaServiceException;

public class ImageCaptchaServlet extends HttpServlet
{
    private static final long serialVersionUID = 1L;

    public void init(ServletConfig servletConfig) throws ServletException
    {
        super.init(servletConfig);
    }

    protected void doGet(HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) throws ServletException,
            IOException
    {
        genernateCaptchaImage(httpServletRequest, httpServletResponse);
    }

    private void genernateCaptchaImage(final HttpServletRequest request,
            final HttpServletResponse response) throws IOException
    {
        response.setHeader("Cache-Control", "no-store");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setContentType("image/jpeg");
        ServletOutputStream out = response.getOutputStream();
        try {
            String captchaId = request.getSession(true).getId();
            BufferedImage challenge = (BufferedImage) CaptchaServiceSingleton
                    .getInstance().getChallengeForID(captchaId,
                            request.getLocale());
            ImageIO.write(challenge, "jpg", out);
            out.flush();
        }
        catch (CaptchaServiceException e) {
        }
        finally {
            out.close();
        }
    }
}