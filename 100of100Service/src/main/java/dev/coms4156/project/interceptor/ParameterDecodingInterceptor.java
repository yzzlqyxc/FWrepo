package dev.coms4156.project.interceptor;

import java.util.Base64;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * This class decodes the client ID parameter in the request.
 * Citation:
 * I was inspired by the following blog post to decode parameter in Java:
 * <a href="https://medium.com/@snyksec/secure-java-url-encoding-and-decoding-1e8b3eac669b">
 *   decode</a>
 */
@Component
public class ParameterDecodingInterceptor implements HandlerInterceptor {
  private static final Logger logger = LoggerFactory.getLogger(ParameterDecodingInterceptor.class);
  private static final String CLIENT_ID = "cid";

  @Override
  public boolean preHandle(
      HttpServletRequest request,
      HttpServletResponse response,
      Object handler
  ) {
    String originalCid = request.getParameter(CLIENT_ID);

    if (originalCid != null) {
      String decodedCid = decode(originalCid);
      request.setAttribute(CLIENT_ID, decodedCid);
      logger.info("Decode client id from [{}] to [{}].", originalCid, decodedCid);
    }

    return true;
  }

  /**
   * Decodes a URL-encoded string.
   *
   * @param decoded the to-be-encoded string
   * @return the encoded string
   */
  public static String encode(String decoded) {
    return Base64.getUrlEncoder().withoutPadding().encodeToString(decoded.getBytes());
  }

  /**
   * Decodes a URL-encoded string.
   *
   * @param encoded the to-be-decoded string
   * @return the decoded string
   */
  public static String decode(String encoded) {
    return new String(Base64.getUrlDecoder().decode(encoded));
  }

}
