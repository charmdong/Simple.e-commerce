package com.commerce.service;

import com.commerce.controller.form.ItemForm;
import com.commerce.domain.Item;
import com.commerce.dto.ItemDto;
import com.commerce.repository.ItemRepository;
import com.commerce.util.ExceptionUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    @Transactional
    public void saveItem(Item item) {
        itemRepository.save(item);
    }

    @Transactional
    public void updateItem(Long id, ItemForm form) {
        Optional<Item> findItem = itemRepository.findById(id);

        if (findItem.isEmpty()) {
            throw new NoSuchElementException(ExceptionUtils.ITEM_NOT_FOUND);
        }

        Item item = findItem.get();
        item.changeInfo(form);
    }

    public void deleteItem (Long id) {
        itemRepository.deleteById(id);
    }

    public ItemDto findOne(Long id) {
        Optional<Item> findItem = itemRepository.findById(id);

        if (findItem.isEmpty()) {
            throw new NoSuchElementException(ExceptionUtils.ITEM_NOT_FOUND);
        }

        return new ItemDto(findItem.get());
    }

    public List<ItemDto> findItems(String memberId) {
        List<Item> itemList = itemRepository.findByRegId(memberId);
        return itemList.stream().map(ItemDto::new).collect(Collectors.toList());
    }

    public List<ItemDto> findAllItems () {
        List<Item> itemList = itemRepository.findAll();
        return itemList.stream().map(ItemDto::new).collect(Collectors.toList());
    }
}
