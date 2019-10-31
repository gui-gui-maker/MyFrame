package demo.crud.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.khnt.core.crud.web.SpringSupportAction;
import com.khnt.core.crud.web.support.JsonWrapper;
import com.khnt.core.exception.KhntException;

import demo.crud.bean.Demo;
import demo.crud.service.DemoManager;

/**
 * <p>
 * web控制器组件。该组件继承自泛型类SpringSupportAction，并提供了运行时的bean和manager对象。
 * 由此获得了对bean的crund操作能力，SpringSupportAction类中已经定义了对bean的 save,detail,delete方法。
 * 
 * </p>
 * <p>
 * 注解@Controller标识该类为web 控制器；
 * </p>
 * <p>
 * 注解@RequestMapping定义该控制器的web访问路径
 * </p>
 */
@Controller
@RequestMapping("demo/crud/")
public class DemoController extends SpringSupportAction<Demo, DemoManager> {

	// 必须提供Demo实体的manager对象，使用注解@Autowired标识为自动装配
	@Autowired
	DemoManager demoManager;

	/**
	 * <p>
	 * 接受一个ajax请求的方法。该方法将返回一个map。map中的值都将被转化为json文本。
	 * </p>
	 * <p>
	 * 注解@RequestMapping定义该方法的访问路径。最终这个方法的访问路径为：
	 * </p>
	 * <p>
	 * app/crud/demo/ajaxRquest
	 * </p>
	 * <p>
	 * 可见，最终的访问路径由class上的@RequestMapping加上method上的@RequestMapping组合而成。
	 * 而最终实际应用中，将还会有全局配置的路径前缀和后缀，比如前缀为“/”，后缀为“.do”，那么这个方法的实际url为
	 * http://localhost:8080/app/demo/crud/ajaxRquest.do
	 * </p>
	 * <p>
	 * 注解@ResponseBody标识该方法为ajax响应，并且响应的结果是json文本。mime类型为application/json
	 * </p>
	 * <p>
	 * 参数param可以从url中的parameter获取，比如请求URL为
	 * http://localhost:8080/app/demo/crud/ajaxRquest.do?param=testvalue
	 * 那么param的值将会是“testvalue”
	 * </p>
	 * <p>
	 * 该方法执行完成之后响应给客户端的json文本格式为： {success:false,msg:""}
	 * </p>
	 */
	@RequestMapping("ajaxRquest")
	@ResponseBody
	public Map<String, Object> ajaxRequest(String param) throws Exception {

		// 使用辅助工具类JsonWrapper实现结果包装，这里通过successWrapper方法将会获得一个map对象，
		// map中有一个key/value,其值为success:true,
		// 该方法也可接受任意多个对象，最终这些对象将会被序列化为json文本

		Object data = new Object();

		return JsonWrapper.successWrapper(data);
	}

	// ==================================================
	// 更多的方法配置，这里我们列出集中常用的配置方法

	/**
	 * <p>
	 * spring mvc标准的配置方式。
	 * </p>
	 * <p>
	 * ModelAndView是一个包含了model和view的对象。方法执行完成之后，可自行构建这样一个对象，返回给spring mvc。
	 */
	@RequestMapping("mavRquest")
	public ModelAndView mavRquest(String param) throws Exception {
		// 构建一个model对象，model对象实际就是一个map，里面可放入key/value值，表示返回给视图的数据，
		// 这些key/value最终都会以request对象的attribute方式获取。
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("attr1", "值1");
		model.put("attr2", "值2");

		// 构造一个modelAndView并返回
		// 这里第一个参数表示视图的名称，通常为视图文件的相对路径，不包含后缀。该路径的相对目录为全局配置。通常就是webroot
		// 第二个参数是数据模型，其实就是一个map，最终在视图中（比如jsp），可直接在request对象中通过getAttribute获的。
		return new ModelAndView("demo/mvc/modelAndView", model);
	}

	/**
	 * 该方法同上面的mavRquest方法等价。
	 * 
	 * 这里的参数列表中，可以声明一个HttpServletRequest，运行时将会自动获得本次请求的requet对象，
	 * 当然也可以声明response对象。
	 * 
	 * 返回的string就是一个视图名称
	 */
	@RequestMapping("mavRquest2")
	public String mavRequest2(HttpServletRequest request, String param) throws Exception {
		request.setAttribute("attr1", "值1");
		request.setAttribute("attr2", "值2");
		return "demo/mvc/modelAndView";
	}

	/**
	 * 该方法同上面的ajaxRequest方法等价。
	 * 
	 * 返回的string就是一个JSON文本.
	 */
	@RequestMapping("ajaxRquest2")
	@ResponseBody
	public String ajaxRequest2() throws Exception {
		// 注意自行构建json时，需要将key和所有字符值用双引号包住。
		// 如果json较长时，请使用使用StringBuilder，或者使用JSONObject构建

		return "{\"success\":true}";
	}

}
