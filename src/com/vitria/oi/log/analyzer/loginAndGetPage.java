package com.vitria.oi.log.analyzer;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.jsoup.Connection;
import org.jsoup.Connection.Method;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class loginAndGetPage {

	public static void main(String[] args) throws Exception {

		String url = "http://help.vitria.com/index.asp";
		loginAndGetPage http = new loginAndGetPage();
		String reportAProblem = "http://help.vitria.com/content/report_a_problem_main.asp";
		String vitriaOI = "http://help.vitria.com/content/o_report_a_problem.asp";
		Map<String, String> cookies = http.sendPost(url);
		http.sendGet(reportAProblem, cookies);
		Map<String, String> hiddenParamMap = http.findHiddenParams(http.sendGet(vitriaOI, cookies));
		System.out.println(hiddenParamMap.toString());
		//http.JSOUPpostRequest("http://help.vitria.com/content/o_report_a_problem.asp", cookies, hiddenParamMap);
	}

	private Map<String, String> findHiddenParams(Document doc) {
		Element body = doc.body();
		Elements inputs = body.select("input[type=hidden]");
		Map<String, String> hiddenParamMap = new HashMap<String, String>();
		int i = 0;
		for(Element el : inputs){
			if(i++ > 5) break;
			hiddenParamMap.put(el.attr("name"), el.attr("value"));
			System.out.println(el.attr("name") +" : "+ el.attr("value"));
		}
		return hiddenParamMap;
	}

	private Map<String, String> sendPost(String url) 
			throws Exception {
		Connection.Response res = Jsoup.connect(url)
				.data("username", "anazeer")
				.data("password", "tr1vandrum")
				.data("NextURL", "")
				.data("Submit", "Log+In")
				.method(Method.POST)
				.execute();

		Document doc = res.parse();
		Elements header = doc.getElementsByClass("header1");
		System.out.println(header.text());
		Map<String, String> cookies = res.cookies();
		return cookies;
	}

	private Document sendGet(String url, Map<String, String> cookies) throws Exception {

		Document doc = Jsoup.connect(url)
			    .cookies(cookies)
			    .get();
		return doc;
	}

	public void JSOUPpostRequest(String url, Map<String, String> cookies, Map<String, String> hiddenParamMap){

		try {
			Document doc = Jsoup.connect(url).
			data(hiddenParamMap).
			data("AgreementType","Expert Agreement").
			data("SFDCClientName","Starbucks Coffee Company").
			data("ProductName","6396").
			data("txtproductvalue","VOI Business Process Management(BPM)").
			data("OS","RHEL 5.5 64 bit").
			data("ProductVersion","4.0").
			data("CasePriority","3 - Degraded Operation").
			data("ComponentFamily","6427").
			data("txtCFamilyvalue","Others").
			data("ProjectName","Test").
			data("ComponentName","6524").
			data("txtComponentvalue","Database").
			data("ProjectProductionStatus","Not In Production").
			data("ComponentVersion","N/A").
			data("PreferredContactMethod","Email").
			data("AppServer","RedHat JBoss 4.2").
			data("Reproducible","Recurring").
			data("Subject","Test").
			data("Description","Testing monitoring services").
			data("remLen","31973").
			userAgent("Mozilla").
			cookies(cookies).
			post();
			
			System.out.println(doc.toString().contains("success"));

		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 

	}

}