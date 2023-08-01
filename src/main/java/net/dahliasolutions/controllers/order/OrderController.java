package net.dahliasolutions.controllers.order;

import lombok.RequiredArgsConstructor;
import net.dahliasolutions.models.order.OrderNote;
import net.dahliasolutions.models.order.OrderRequest;
import net.dahliasolutions.models.user.User;
import net.dahliasolutions.services.order.OrderService;
import net.dahliasolutions.services.order.OrderNoteService;
import net.dahliasolutions.services.order.OrderItemService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final OrderNoteService orderNoteService;
    private final OrderItemService orderItemService;

    @ModelAttribute
    public void addAttributes(Model model) {
        model.addAttribute("moduleTitle", "Orders");
        model.addAttribute("moduleLink", "/order");
    }

    @GetMapping("")
    public String getUserOrders(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) auth.getPrincipal();
        List<OrderRequest> orderList = orderService.findAllByUser(user);
        model.addAttribute("orderList", orderList);
        return "order/order";
    }

    @GetMapping("/{id}")
    public String getUserOrders(@PathVariable BigInteger id, Model model) {
        Optional<OrderRequest> order = orderService.findById(id);
        List<OrderNote> noteList = orderNoteService.findByOrderId(id);

        model.addAttribute("orderRequest", order.get());
        model.addAttribute("noteList", noteList);
        return "order/order";
    }

}
