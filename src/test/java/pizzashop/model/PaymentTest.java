package pizzashop.model;

import org.junit.jupiter.api.Test;
import pizzashop.repository.MenuRepository;
import pizzashop.repository.PaymentRepository;
import pizzashop.service.PizzaService;

import static org.junit.jupiter.api.Assertions.*;

class PaymentTest {

    @Test
    void test1() {
        Payment payment = new Payment(1, PaymentType.Cash, 10);
        assertEquals(payment.getTableNumber(), 1);
    }

    @Test
    void test2() {
        Payment payment = new Payment(1, PaymentType.Cash, 10);
        assertEquals(payment.getType(), PaymentType.Cash);
    }

}