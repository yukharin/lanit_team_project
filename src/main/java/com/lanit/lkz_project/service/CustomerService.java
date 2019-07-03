package com.lanit.lkz_project.service;

import com.lanit.lkz_project.entities.BookUtils;
import com.lanit.lkz_project.entities.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class CustomerService {

    final private List<Customer> books = BookUtils.build(200);

    public Page<Customer> findPaginated(Pageable pageable) {
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<Customer> list;

        if (books.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, books.size());
            list = books.subList(startItem, toIndex);
        }

        Page<Customer> bookPage
                = new PageImpl<>(list, PageRequest.of(currentPage, pageSize), books.size());

        return bookPage;
    }
}