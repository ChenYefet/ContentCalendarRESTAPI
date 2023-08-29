package com.example.ContentCalendarRESTAPI.controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.ContentCalendarRESTAPI.model.Content;
import com.example.ContentCalendarRESTAPI.repository.ContentCollectionRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/content")
@CrossOrigin
public class ContentController
{
	private final ContentCollectionRepository repository;
	
	public ContentController(ContentCollectionRepository aRepository)
	{
		this.repository = aRepository;
	}
	
	@GetMapping("")
	public List<Content> findAll()
	{
		return this.repository.findAll();
	}
	
	@GetMapping("/{id}")
	public Content findByID(@PathVariable Integer id)
	{
		return this.repository.findByID(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Content not found!"));
	}
	
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping("")
	public void create(@Valid @RequestBody Content content)
	{
		this.repository.save(content);
	}
	
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PutMapping("/{id}")
	public void update(@RequestBody Content content, @PathVariable Integer id)
	{
		if(!this.repository.existsByID(id))
		{
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Content not found!");
		}
		this.repository.save(content);
	}
	
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Integer id)
	{
		this.repository.delete(id);
	}
	
}
