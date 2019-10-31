package demo.script;

import org.junit.Test;

import com.khnt.pub.script.KHScriptEngine;

public class TestKHScriptEngine {

	@Test
	public void testExcuteScript() throws Exception {
		String script = 
				  "function testFun(arg){"
				+ "   var a = 12; "
				+ "	  var b = 23; "
				+ "	  if(arg==1){"
				+ "	      return a;"
				+ "	  }else {"
				+ "		  return b;"
				+ "	  }"
				+ "}"
				+ "testFun(aaa);";
		Object r = KHScriptEngine.excuteScript(script, KHScriptEngine.ENGINE_TYPE_JAVASCRIPT, "{\"aaa\":2}");
		System.out.println(r);
	}

	@Test
	public void testParseContext() {
	}

	@Test
	public void testParseJsonContext() {
	}

}
