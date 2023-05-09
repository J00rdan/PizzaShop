package pizzashop.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pizzashop.model.PaymentType;
import pizzashop.repository.MenuRepository;
import pizzashop.repository.PaymentRepository;

import static org.junit.jupiter.api.Assertions.*;

class PizzaServiceTest {

    MenuRepository repoMenu;
    PaymentRepository payRepo;
    PizzaService service;

    @Test
    void test1() {
        repoMenu = new MenuRepository();
        payRepo = new PaymentRepository("C:\\Users\\drago\\OneDrive\\Desktop\\VVSS\\lab3\\PizzaShop\\data\\paymentsTest.txt", true);
        service = new PizzaService(repoMenu, payRepo);

        double total = service.getTotalAmount(PaymentType.Cash);
        assertEquals(0, total);
    }

    @Test
    void test2() {
        repoMenu = new MenuRepository();
        payRepo = new PaymentRepository("C:\\Users\\drago\\OneDrive\\Desktop\\VVSS\\lab3\\PizzaShop\\data\\paymentsTest.txt");
        service = new PizzaService(repoMenu, payRepo);

        double total = service.getTotalAmount(PaymentType.Cash);
        assertEquals(0, total);
    }

    @Test
    void test3() {
        repoMenu = new MenuRepository();
        payRepo = new PaymentRepository();
        service = new PizzaService(repoMenu, payRepo);

        double total = service.getTotalAmount(PaymentType.Cash);
        System.out.println(total);
        assertNotEquals(0, total);
    }

    @Test
    void test4() {
        repoMenu = new MenuRepository();
        payRepo = new PaymentRepository("C:\\Users\\drago\\OneDrive\\Desktop\\VVSS\\lab3\\PizzaShop\\data\\paymentsTest4.txt");
        service = new PizzaService(repoMenu, payRepo);

        double total = service.getTotalAmount(PaymentType.Cash);
        assertEquals(0, total);
    }
}