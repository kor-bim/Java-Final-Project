package kr.or.ddit.commons.vo;

import java.io.Serializable;

@SuppressWarnings("serial")
public class MenuVO implements Serializable {
	
	public MenuVO() {
		super();
	}
	private MenuVO(String menuText, String menuPath, String menuURI, String menuName) {
		super();
		this.menuName = menuName;
		this.menuURI = menuURI;
	}
	
	private String menuURI;
	private String menuName;

	public String getMenuName() {
		return menuName;
	}
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	public String getMenuURI() {
		return menuURI;
	}
	public void setMenuURI(String menuURI) {
		this.menuURI = menuURI;
	}
	
	@Override
	public String toString() {
		return "menuVO [menuName=" + menuName + "]";
	}

	public static MenuVOBuilder getBuilder(){
		return new MenuVOBuilder();
	}
	public static class MenuVOBuilder{
		private String menuText;
		private String menuPath;
		private String menuURI;
		private String menuName;
		
		public MenuVOBuilder menuText(String menuText){
			this.menuText = menuText;
			return this;
		}
		public MenuVOBuilder menuPath(String menuPath){
			this.menuPath = menuPath;
			return this;
		}
		public MenuVOBuilder menuURI(String menuURI){
			this.menuURI = menuURI;
			return this;
		}
		public MenuVOBuilder menuName(String menuName){
			this.menuName = menuName;
			return this;
		}
		
		public MenuVO build() {
			return new MenuVO(menuText, menuPath, menuURI, menuName);
		}
	}
	
	
	
}
