//changes for git test


public class BookChapter {
	
	public static void main(String[] args)
	{
		System.out.println(nearestChapter(new Chapter[] {new Chapter("Chapter 1a", 1), new Chapter("Chapter 1b", 5)}, 3));
	}
	
	public static String nearestChapter(Chapter[] chapter, int page) {
		//loop through objects, get the page number, compare to page, if the difference is smaller
		// than the value saved, get the chapter & overwrite the value
		
		String result = "";
		int distance = 0, closest = 100000;
		
		for(int i = 0; i < chapter.length; i++) {
			distance = Math.abs(chapter[i].getPage() - page);
			if(distance <= closest) {
					closest = distance;
					result = chapter[i].getName();
			}

		}
		
		return result;
	}
}

class Chapter {
	private String name;
	private int page;
	
	public Chapter(String name, int page) {
		this.name = name;
		this.page = page;
	}
	public String getName() { return name; }
	public void setName(String name) { this.name = name; }
	public int getPage() { return page; }
	public void setPage(int page) { this.page = page; }
}
