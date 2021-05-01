package hello.itemservice.web.basic;

import hello.itemservice.domain.Item;
import hello.itemservice.domain.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.PostConstruct;

@Controller
@RequestMapping("/basic/items")
@RequiredArgsConstructor
public class basicItemController {

    private final ItemRepository itemRepository;

    @GetMapping
    public String items(Model model) {
        model.addAttribute("items", itemRepository.findAll());
        return "basic/items";
    }

    @GetMapping("/{itemId}")
    public String item(@PathVariable long itemId, Model model) {
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item",item);
        return "basic/item";
    }

    @GetMapping("/add")
    public String addForm() {
        return  "basic/addForm";
    }

//    @PostMapping("/add")
//    public String save(Item item) {
//        //객체에 경우에는 @ModelAttribute 생략가능.
//        //class의 명에 첫글자를 소문자로 바꿔서 모델어트리뷰트에 담긴다.
//        itemRepository.save(item);
//        // 자동추가
//        //model.addAttribute("item",item);
//        return "redirect:/basic/items/" + item.getId();
//    }

    @PostMapping("/add")
    public String saveV2(Item item, RedirectAttributes redirectAttributes) {
        Item save = itemRepository.save(item);
        redirectAttributes.addAttribute("itemId", save.getId());
        redirectAttributes.addAttribute("status", true);
        return "redirect:/basic/items/{itemId}";
        //status는 쿼리파라미터로 넘어
    }

    @GetMapping("/{itemId}/edit")
    public String editForm(@PathVariable Long itemId, Model model) {
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item",item);
        return "basic/editForm";
    }

    @PostMapping("{itemId}/edit")
    public String edit(@PathVariable Long itemId, @ModelAttribute Item item) {
        itemRepository.update(itemId,item);
        return "redirect:/basic/items/{itemId}";
    }


    @PostConstruct
    public void init() {
        itemRepository.save(new Item("itemA",10000,10));
        itemRepository.save(new Item("itemB",20000,20));
    }
}
