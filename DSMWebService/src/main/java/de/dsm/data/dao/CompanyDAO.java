package de.dsm.data.dao;

import java.util.List;

import de.dsm.data.entity.Company;

public interface CompanyDAO {
	public Company getForId(Long id);
	public List<Company> getAll();
}
