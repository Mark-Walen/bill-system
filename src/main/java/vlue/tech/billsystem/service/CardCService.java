package vlue.tech.billsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vlue.tech.billsystem.mapper.CardCategoryMapper;
import vlue.tech.billsystem.pojo.CardCategory;

@Service
public class CardCService extends CategoryService<CardCategory, CardCategoryMapper>{

    private CardCategoryMapper categoryMapper;

    @Autowired
    public CardCService(CardCategoryMapper categoryMapper) {
        super(categoryMapper);
    }
}
