package py.blog.base.view;

import org.springframework.web.servlet.view.freemarker.FreeMarkerView;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @description 扩展FreeMarkerView
 * @author wragony
 * @email: wragony@163.com
 * @createDate: 2014年3月7日
 * @version 1.0.0
 */
public class ExFreeMarkerView extends FreeMarkerView {
	
	private static final String CONTEXT_PATH = "base";
	
	@Override
	protected void exposeHelpers(Map<String, Object> model, HttpServletRequest request) throws Exception {
	
		model.put(CONTEXT_PATH, request.getContextPath());
		super.exposeHelpers(model, request);
	}
}
