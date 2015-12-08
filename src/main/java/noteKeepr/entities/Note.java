package noteKeepr.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;


@Entity
public class Note implements Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;

    private String content;

    private Long ownerId;

	private Date dateCreated;
	private Date dateModified;

    @ManyToMany(mappedBy = "notes")
    private Set<Account> collaborators;

    public Note() {
        this.content = "";
    }

    public Note(String content, Long ownerId) {
        this.content = content;
        this.ownerId = ownerId;
    }

    @PreRemove
    private void removeNotesFromAccounts() {
        for (Account u : collaborators) {
            u.getNotes().remove(this);
        }
    }

    public Note (String content)
    {
        this.content = content;
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getContent()
    {
        return content;
    }

    public void setContent(String content)
    {
        this.content = content;
    }

    public Long getOwnerId()
    {
        return ownerId;
    }

    public void setOwnerId(Long ownerId)
    {
        this.ownerId = ownerId;
    }

    public Set<Account> getCollaborators()
    {
        return collaborators;
    }

    public void setCollaborators(Set<Account> collaborators)
    {
        this.collaborators = collaborators;
    }

	public Date getDateCreated()
	{
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated)
	{
		this.dateCreated = dateCreated;
	}

	public Date getDateModified()
	{
		return dateModified;
	}

	public void setDateModified(Date dateModified)
	{
		this.dateModified = dateModified;
	}

}
