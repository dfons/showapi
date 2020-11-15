package com.example.showapi.show.resource;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.RepresentationModel;

import com.example.showapi.show.ShowController;
import com.example.showapi.show.domain.Play;
import com.example.showapi.show.domain.Show;

public class ShowResource extends RepresentationModel<ShowResource> {
	
	private String id;
	private String name;
	private String genre;
	private Integer duration;
	private String description;
	private List<String> cast;
	private List<PlayResource> plays;
	
	public static ShowResource toResource(Show entity) {
		ShowResource resource = new ShowResource();
		resource.setId(entity.getId().toString());
		resource.setName(entity.getName());
		resource.setGenre(entity.getGenre());
		resource.setDuration(entity.getDuration());
		resource.setDescription(entity.getDescription());
		resource.setCast(entity.getCast());
		resource.setPlays(new ArrayList<>());
		for (Play play : entity.getPlays()) {
			resource.getPlays().add(PlayResource.toResource(play));
		}
		
		Link selfRel = linkTo(ShowController.class).slash(entity.getId()).withSelfRel();
		resource.add(selfRel);
		return resource;
	}
	public static Collection<ShowResource> toResource(List<Show> entities) {
		List<ShowResource> resources = new ArrayList<>();
		entities.forEach(entity -> resources.add(toResource(entity)));
		return resources;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	public Integer getDuration() {
		return duration;
	}
	public void setDuration(Integer duration) {
		this.duration = duration;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public List<String> getCast() {
		return cast;
	}
	public void setCast(List<String> cast) {
		this.cast = cast;
	}

	public List<PlayResource> getPlays() {
		return plays;
	}
	public void setPlays(List<PlayResource> plays) {
		this.plays = plays;
	}
	
	@Override
	public String toString() {
		return "ShowResource [id=" + id + ", name=" + name + ", genre=" + genre + ", duration=" + duration
				+ ", description=" + description + ", cast=" + cast + ", plays=" + plays + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((cast == null) ? 0 : cast.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((duration == null) ? 0 : duration.hashCode());
		result = prime * result + ((genre == null) ? 0 : genre.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((plays == null) ? 0 : plays.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		ShowResource other = (ShowResource) obj;
		if (cast == null) {
			if (other.cast != null)
				return false;
		} else if (!cast.equals(other.cast))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (duration == null) {
			if (other.duration != null)
				return false;
		} else if (!duration.equals(other.duration))
			return false;
		if (genre == null) {
			if (other.genre != null)
				return false;
		} else if (!genre.equals(other.genre))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (plays == null) {
			if (other.plays != null)
				return false;
		} else if (!plays.equals(other.plays))
			return false;
		return true;
	}

}
