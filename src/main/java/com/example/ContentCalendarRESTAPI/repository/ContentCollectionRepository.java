package com.example.ContentCalendarRESTAPI.repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.example.ContentCalendarRESTAPI.model.Content;
import com.example.ContentCalendarRESTAPI.model.Status;
import com.example.ContentCalendarRESTAPI.model.Type;

import jakarta.annotation.PostConstruct;

@Repository
public class ContentCollectionRepository
{
	private final List<Content> contentList = new ArrayList<>();
	
	public ContentCollectionRepository()
	{
		
	}
	
	public List<Content> findAll()
	{
		return this.contentList;
	}
	
	public Optional<Content> findByID(Integer id)
	{
		return this.contentList.stream().filter(c -> c.id().equals(id)).findFirst();
	}
	

	public void save(Content content)
	{
		this.contentList.removeIf(c -> c.id().equals(content.id()));
		this.contentList.add(content);
	}
	
	@PostConstruct
	private void initialise()
	{
		Content content = new Content(1, "My First Blog Post", "My first blog post", Status.IDEA, Type.ARTICLE, LocalDateTime.now(), null, "");
		this.contentList.add(content);
	}

	public boolean existsByID(Integer id)
	{
		return this.contentList.stream().filter(c -> c.id().equals(id)).count() == 1;
	}

	public void delete(Integer id)
	{
		this.contentList.removeIf(c -> c.id().equals(id));		
	}
}
