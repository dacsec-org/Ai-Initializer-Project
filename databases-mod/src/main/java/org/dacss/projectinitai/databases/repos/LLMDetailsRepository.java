package org.dacss.projectinitai.databases.repos;

import org.dacss.projectinitai.databases.LLMDetails;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * <h1>{@link LLMDetailsRepository}</h1>
 * Repo for LLM details.
 */
public interface LLMDetailsRepository extends JpaRepository<LLMDetails, String> {}
