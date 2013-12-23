package arctouch.challenge.model;

public class Departure implements Comparable<Departure> {
	public enum TypeOfDay
    {
        WEEKDAY,
        SATURDAY,
        SUNDAY
    }

    private int id;
    private TypeOfDay calendar;
    private String time;

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public TypeOfDay getCalendar()
    {
        return calendar;
    }

    public void setCalendar(TypeOfDay calendar)
    {
        this.calendar = calendar;
    }

    public String getTime()
    {
        return time;
    }

    public void setTime(String time)
    {
        this.time = time;
    }

	@Override
	public int compareTo(Departure another) {
		int result;
		if ( this == another ) {
			result = 0; //optimization for comparing same reference
		}
		else {
			int calendarComparison = this.getCalendar().compareTo(another.getCalendar());
			if (calendarComparison != 0) {
				result = calendarComparison; //calendar is different, return calendar difference
			}
			else {
				result = this.getTime().compareTo(another.getTime()); //same calendar, evaluate time
			}
		}
		
		return result;
	}
}