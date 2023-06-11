package book.manager.initializer;

import book.manager.config.MvcConfiguration;
import book.manager.config.RootConfiguration;
import book.manager.config.SecurityConfiguration;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.ServletException;

public class MvcInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    /**
     * 有了SpringSecurity，可以写到Security的初始化器里面beforeXXX
     * @param servletContext
     * @throws ServletException
     */
//    @Override
//    public void onStartup(ServletContext servletContext) throws ServletException {
//        servletContext.addFilter("characterEncodingFilter",new CharacterEncodingFilter("UTF-8",true))
//                .addMappingForUrlPatterns(null,false,"/*");
//        super.onStartup(servletContext);
//    }

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{RootConfiguration.class, SecurityConfiguration.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{MvcConfiguration.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

}
