package org.lzmhc.service;

import org.lzmhc.entity.ToDo;
import org.lzmhc.repository.NotesRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class NoteService {
    private final NotesRepository notesRepository;

    public NoteService(NotesRepository notesRepository) {
        this.notesRepository = notesRepository;
    }
    public void save(ToDo note) {
        notesRepository.save(note);
    }
    @Transactional
    public void deleteNoteById(String id) {
        notesRepository.deleteById(id);
    }
    public List<ToDo> findAll() {
        return notesRepository.findAll();
    }
}
