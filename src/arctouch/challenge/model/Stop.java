package arctouch.challenge.model;

public class Stop implements Comparable<Stop>{
    private int id;
    private String name;
    private int sequence;
    private int route_id;

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public int getSequence()
    {
        return sequence;
    }

    public void setSequence(int sequence)
    {
        this.sequence = sequence;
    }

    public int getRoute_id()
    {
        return route_id;
    }

    public void setRoute_id(int route_id)
    {
        this.route_id = route_id;
    }

	@Override
	public int compareTo(Stop another) {
		int result;
		
		if ( this == another ) {
			result = 0; //optimization for comparing same reference
		}
		else {
			int thisSequence = this.getSequence();
			int anotherSequence = another.getSequence();
			
			if (thisSequence < anotherSequence) {
				result = -1; //this object is before
			}
			else {
				if (thisSequence > anotherSequence) {
					result = 1; //this object is after
				}
				else {
					result = 0; //they're the same
				}
			}
		}
		
		return result;
	}
}