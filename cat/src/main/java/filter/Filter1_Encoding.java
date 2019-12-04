//201802104058潘英祖
package filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
//过滤器名字为Filter 1，对所有请求的资源有效
@WebFilter(filterName = "Filter 1", urlPatterns = {"/*"})
public class Filter1_Encoding implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}

    @Override
    public void destroy() {}

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
        System.out.println("Filter 1 - encoding begins");
        //将参数servletRequest强制类型转换为HttpServletRequest，声明HttpServletRequest变量request指向它
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        //将参数servletResponse强制类型转换为HttpServletResponse，声明HttpServletResponse变量response指向它
        HttpServletResponse response=(HttpServletResponse)servletResponse;
        //获得请求路径
        String path= request.getRequestURI();
        //获得请求的方法名
        String method=request.getMethod();
        //判断请求的路径是否包含login，若包含则提示不设置字符编码
        if (path.contains("/login")){
            System.out.println("未设置字符编码格式");
        }else {//若路径符合条件，则首先设置响应对象字符编码格式为utf8
            response.setContentType("text/html;charset=UTF-8");
            System.out.println("设置响应对象字符编码格式为utf8");
            //对调用的方法进行判断，若为post或put则设置请求对象字符编码格式为utf8
            if (method.equals("POST")||method.equals("PUT")){
                request.setCharacterEncoding("UTF-8");
                System.out.println("设置请求对象字符编码格式为utf8");
            }
        }
        //执行其他过滤器，若其他过滤器执行完毕则执行原请求
        chain.doFilter(servletRequest,servletResponse);
        System.out.println("Filter 1 - encoding ends");
    }
}
