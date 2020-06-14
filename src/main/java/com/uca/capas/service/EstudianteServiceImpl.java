package com.uca.capas.service;

import java.util.List;


import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.uca.capas.domain.Estudiante;
import com.uca.capas.repositories.EstudianteRepo;

@Service
public class EstudianteServiceImpl implements EstudianteService {
	
	@Autowired
	EstudianteRepo estudianteRepo;

	@Override
	public List<Estudiante> findAll() throws DataAccessException {
		return estudianteRepo.findAll();
	}

	@Override
	@Transactional
	public void save(Estudiante estudiante) throws DataAccessException {
		estudianteRepo.save(estudiante);
	}

	@Override
	@Transactional
	public void delete(Integer cUsuario) throws DataAccessException {
		estudianteRepo.deleteById(cUsuario);
	}

	@Override
	public Estudiante findOne(Integer cUsuario) throws DataAccessException {
		return estudianteRepo.getOne(cUsuario);
	}	
}
