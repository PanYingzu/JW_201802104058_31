//201802104058潘英祖
package filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Calendar;
//过滤器名字为Filter 2，对所有请求有效
@WebFilter(filterName = "Filter 2", urlPatterns = {"/*"})
public class Filter2_Date implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}

    @Override
    public void destroy() {}

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
        System.out.println("Filter 2 - date begins");
        //将参数servletRequest强制类型转换为HttpServletRequest，声明HttpServletRequest变量request指向它
        HttpServletRequest request=(HttpServletRequest)servletRequest;
        //获得请求路径
        String path=request.getRequestURI();
        //创建Calendar类的对象
        Calendar cal = Calendar.getInstance();
        //根据Calendar对象，创建时间信息的字串
        String time= cal.get(Calendar.YEAR)+"年"+(cal.get(Calendar.MONTH) + 1)+"月"+cal.get(Calendar.DATE)+"日"+cal.get(Calendar.HOUR_OF_DAY)+": "+cal.get(Calendar.MINUTE);
        //打印路径与时间
        System.out.println(path+" @ "+time);
        //执行其他过滤器，若其他过滤器执行完毕则执行原请求
        chain.doFilter(servletRequest,servletResponse);
        System.out.println("Filter 2 - date ends");
    }
}
