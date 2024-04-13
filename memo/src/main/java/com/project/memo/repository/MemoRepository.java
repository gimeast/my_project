package com.project.memo.repository;

import com.project.memo.domain.entity.Memo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemoRepository extends JpaRepository<Memo, Long>, MemoRepositoryCustom {

}
