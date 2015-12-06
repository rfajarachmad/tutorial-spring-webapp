package com.fajarachmad.tutorial.viewmodel;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.zkoss.bind.annotation.Init;

import com.fajarachmad.tutorial.common.NavigationPage;

@Controller
@RequestMapping("/")
public class MainViewModel {

	NavigationPage currentPage;
	private Map<String, Map<String, NavigationPage>> pageMap;

	@Init
	public void init() {
		//initPageMap();
		//currentPage = pageMap.get("ZK's Pizza").get("About Us");
	}

	public Map<String, Map<String, NavigationPage>> getPageMap() {
		return pageMap;
	}

	public NavigationPage getCurrentPage() {
		return currentPage;
	}

	private void initPageMap() {
		pageMap = new LinkedHashMap<String, Map<String, NavigationPage>>();
		
		//pageMap.put("", "");
		
		/*addPage("ZK's Pizza", "About Us", "/home/about_us.zul");
		addPage("ZK's Pizza", "Menu", "/home/menu.zul");
		addPage("ZK's Pizza", "FAQ", "/home/faq.zul");
		addPage("ZK's Pizza", "Press", "/home/press.zul");

		addPage("Customers", "Active Members", "/customers/customers.zul",
				"active");
		addPage("Customers", "Inactive Members", "/customers/customers.zul",
				"inactive");

		addPage("Orders", "In Preparation", "/orders/orders.zul",
				"in-preparation");
		addPage("Orders", "Ready for Shipping", "/orders/orders.zul", "ready");
		addPage("Orders", "Shipping", "/orders/orders.zul", "shipping");
		addPage("Orders", "Specified for Later", "/orders/orders.zul", "later");

		addPage("Fan Service", "Events", "/fan_service/events.zul");
		addPage("Fan Service", "Promotion", "/fan_service/promotion.zul");*/
	}

	private void addPage(String title, String subTitle, String includeUri) {
		addPage(title, subTitle, includeUri, null);
	}

	private void addPage(String title, String subTitle, String includeUri,
			String data) {
		String folder = "/widgets/menu/navbar/pages";
		Map<String, NavigationPage> subPageMap = pageMap.get(title);
		if (subPageMap == null) {
			subPageMap = new LinkedHashMap<String, NavigationPage>();
			pageMap.put(title, subPageMap);
		}
		NavigationPage navigationPage = new NavigationPage(title, subTitle,
				folder + includeUri + "?random=" + Math.random(), data) {
			@Override
			public boolean isSelected() {
				return currentPage == this;
			}
		};

	}
}
