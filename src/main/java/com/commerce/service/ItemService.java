package com.commerce.service;

import com.commerce.controller.form.ItemForm;
import com.commerce.domain.Item;
import com.commerce.vo.ItemVO;
import com.commerce.repository.ItemRepository;
import com.commerce.util.ExceptionUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Slf4j
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
        Item item = itemRepository.findById(id).orElseThrow(() -> new NoSuchElementException(ExceptionUtils.ITEM_NOT_FOUND));
        item.changeInfo(form);
    }

    @Transactional
    public void deleteItem (Long id) {
        itemRepository.deleteById(id);
    }

    public ItemVO findOne(Long id) {
        Item findItem = itemRepository.findById(id).orElseThrow(() -> new NoSuchElementException(ExceptionUtils.ITEM_NOT_FOUND));
        return new ItemVO(findItem);
    }

    public List<ItemVO> findItems(String memberId) {
        List<Item> itemList = itemRepository.findByRegId(memberId);
        return itemList.stream().map(ItemVO::new).collect(Collectors.toList());
    }

    public List<ItemVO> findAllItems () {
        List<Item> itemList = itemRepository.findAllItemList();
        return itemList.stream().map(ItemVO::new).collect(Collectors.toList());
    }
}
