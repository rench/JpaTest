
package xin.lowang.api;

import java.util.Iterator;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

/**
 * 文件上传
 * @author Wang.ch
 *
 */
//@RestController
@Controller
@RequestMapping("/api/file")
public class FileUploadController {
    @Autowired
    private ServletContext servletContext;

    /**
     * 文件上传
     * @param request
     * @return
     * @throws Exception
     */
    @PostMapping("/upload")
    @ResponseBody
    public ResponseEntity<?> uploadFile(HttpServletRequest request) throws Exception {
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        String username = "123";// SecurityContextHolder.getContext().getAuthentication().getName();
        String savePath = "/" + username + "/";
        String extName = "";// 扩展名
        String nowTime = "";//CommonUtil.getNow(6, null);// 当前时间 毫秒数
        String newFileName = "";
        System.out.println(request.getParameter("name"));
        Iterator<String> it = multipartRequest.getFileNames();
        if (it.hasNext()) {
            String fileName = it.next();
            MultipartFile uploadify = multipartRequest.getFile(fileName);
            String filename = uploadify.getOriginalFilename();
        }
        return null;
    }
}
