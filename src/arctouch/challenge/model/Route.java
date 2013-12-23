package arctouch.challenge.model;

import java.util.Calendar;

public class Route {
	private int id;
    private String shortName;
    private String longName;
    private Calendar lastModifiedDate;
    private int agencyId;

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getShortName()
    {
        return shortName;
    }

    public void setShortName(String shortName)
    {
        this.shortName = shortName;
    }

    public String getLongName()
    {
        return longName;
    }

    public void setLongName(String longName)
    {
        this.longName = longName;
    }

    public Calendar getLastModifiedDate()
    {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Calendar lastModifiedDate)
    {
        this.lastModifiedDate = lastModifiedDate;
    }

    public int getAgencyId()
    {
        return agencyId;
    }

    public void setAgencyId(int agencyId)
    {
        this.agencyId = agencyId;
    }
}
