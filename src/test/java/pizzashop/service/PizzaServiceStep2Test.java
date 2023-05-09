package pizzashop.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.mockito.Mock;
import pizzashop.model.Payment;
import pizzashop.model.PaymentType;
import pizzashop.repository.MenuRepository;
import pizzashop.repository.PaymentRepository;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class PizzaServiceStep2Test {
    private PaymentRepository repoPayments;

    private MenuRepository repoMenu;

    private PizzaService pizzaService;

    @Mock
    private Payment mockPayment;

    @BeforeEach
    public void setUp() {
        try {
            File file = new File("data/test.txt");
            file.createNewFile();
            System.out.println("Created");
        } catch(Exception e) {
            e.printStackTrace();
        }
        repoMenu=new MenuRepository();
        repoPayments=new PaymentRepository("data/test.txt");
        pizzaService=new PizzaService(repoMenu,repoPayments);

    }

    @Test
    public void test1(){
        int tableNumber=1;
        PaymentType type=PaymentType.Cash;
        double amount=10.0;
        mockPayment = mock(Payment.class);
        when(mockPayment.getTableNumber()).thenReturn(tableNumber);
        when(mockPayment.getType()).thenReturn(type);
        when(mockPayment.getAmount()).thenReturn(amount);
        pizzaService.addPayment(tableNumber,type,amount);

        assertEquals(pizzaService.getPayments().size(),1);
        assertEquals(pizzaService.getPayments().get(0).getTableNumber(), tableNumber);
    }

    @Test
    public void test2(){
        assertEquals(pizzaService.getPayments().size(),0);
        int tableNumber=1;
        PaymentType type=PaymentType.Cash;
        double amount=10.0;
        mockPayment = mock(Payment.class);
        when(mockPayment.getTableNumber()).thenReturn(tableNumber);
        when(mockPayment.getType()).thenReturn(type);
        when(mockPayment.getAmount()).thenReturn(amount);
        pizzaService.addPayment(tableNumber,type,amount);
        pizzaService.addPayment(tableNumber,type,amount);
        pizzaService.addPayment(tableNumber,type,amount);
        pizzaService.addPayment(tableNumber,type,amount);
        pizzaService.addPayment(tableNumber,type,amount);

        assertEquals(pizzaService.getPayments().size(),5);
    }

    @AfterEach
    public void tearDown() {
        File myObj = new File("data/test.txt");
        if (myObj.delete()) {
            System.out.println("Deleted");
        } else {
            System.out.println("Failed");
        }
    }

}