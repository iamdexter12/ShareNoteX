package com.speer.sharenotex.model;

import java.sql.Timestamp;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entity class representing a SharedNote in the application. Each SharedNote
 * has a unique identifier (ID), information about who shared the note
 * (sharedBy), who the note was shared with (sharedTo), the timestamp when it
 * was shared (sharedAt), and the actual shared Note object (note) stored in
 * JSON format.
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class SharedNote {

	/**
	 * Unique identifier for the shared note.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	/**
	 * User identifier of the person who shared the note.
	 */
	private String sharedBy;

	/**
	 * User identifier of the person with whom the note was shared.
	 */
	private String sharedTo;

	/**
	 * Timestamp indicating when the note was shared.
	 */
	private Timestamp sharedAt;

	/**
	 * Actual Note object stored in JSON format.
	 */

	private Integer noteId;

}
