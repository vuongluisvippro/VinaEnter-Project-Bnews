package bean;

@SuppressWarnings("all")
public class Category {
	private int id_cat;
	private String name;
	
	public Category() {
		super();
	}
	
	public Category(String name) {
		super();
		this.name = name;
	}

	public Category(int id_cat, String name) {
		super();
		this.id_cat = id_cat;
		this.name = name;
	}

	public int getId_cat() {
		return id_cat;
	}
	public void setId_cat(int id_cat) {
		this.id_cat = id_cat;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
