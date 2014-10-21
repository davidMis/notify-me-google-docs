package parser;

// for each cell
// Dont go above Z column
public class EntryDO {

	 int char_low= 65;
	int char_upper = 90;

	private int row=0;
	private int col=0;
	private String content="";

	/**
	 * @return the col
	 */
	public int getCol() {
		return col;
	}

	/**
	 * @param col the col to set
	 */
	public void setCol(int col) {
		this.col = col;
	}

	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}

	public void setValue(String RC, String content){
		if(content==null)
			return;
		this.setContent(content);
		if(RC==null)
			return;
		RC = RC.toUpperCase();
		if(RC.length()==2){
			char C = RC.charAt(0);

			col = ((int) C)-65;
			setRow(Integer.parseInt(RC.substring(1))-1);
		}else{
			char C = RC.charAt(0);

			col = ((int) C)-65;
			setRow(Integer.parseInt(RC.substring(1))-1);
		}

	}

	/**
	 * @return the row
	 */
	public int getRow() {
		return row;
	}

	/**
	 * @param row the row to set
	 */
	public void setRow(int row) {
		this.row = row;
	}

}
