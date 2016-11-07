package univ.donga.dw.main;

public class MainListItem {
	private int iconRes;
	private String text;
	public MainListItem(int iconRes, String text)
	{
		this.iconRes = iconRes;
		this.text = text;
	}
	public int getIconRes() {
		return iconRes;
	}
	public String getText() {
		return text;
	}
}
