package arctouch.challenge.model;

import java.util.List;

public class ApiResponse<T> {
	private List<T> rows;
	private int rowsAffected;
	
	public List<T> getRows()
	{
		return rows;
	}
	
	public void setRows(List<T> rows)
	{
		this.rows = rows;
	}
	
	public int getRowsAffected()
	{
		return rowsAffected;
	}
	
	public void setRowsAffected(int rowsAffected)
	{
		this.rowsAffected = rowsAffected;
	}	
}
