package pizzashop.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import pizzashop.model.Payment;
import pizzashop.model.PaymentType;
import pizzashop.repository.MenuRepository;
import pizzashop.repository.PaymentRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PizzaServiceTest2 {

    @Mock
    private PaymentRepository mockedPaymentRepo;

    @Mock
    private MenuRepository mockedMenuRepo;

    @InjectMocks
    private PizzaService pizzaService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void test1(){
        List<Payment> paymentList = new ArrayList<>();
        paymentList.add(new Payment(1, PaymentType.Cash, 10.0));
        paymentList.add(new Payment(2, PaymentType.Card, 20.0));
        paymentList.add(new Payment(3, PaymentType.Card, 30.0));
        paymentList.add(new Payment(4, PaymentType.Card, 40.0));
        Mockito.when(mockedPaymentRepo.getAll()).thenReturn(paymentList);

        List<Payment> allPayments=pizzaService.getPayments();
        assertEquals(allPayments.size(),paymentList.size());
        assertEquals(allPayments.get(0).getTableNumber(),1);

        Mockito.verify(mockedPaymentRepo, times(1)).getAll();
    }

    @Test
    public void test2() throws Exception {
        int tableNumber=1;
        PaymentType type=PaymentType.Cash;
        double amount=10.0;
        Payment mockPayment = mock(Payment.class);
        when(mockPayment.getTableNumber()).thenReturn(tableNumber);
        when(mockPayment.getType()).thenReturn(type);
        when(mockPayment.getAmount()).thenReturn(amount);

        Mockito.doNothing().when(mockedPaymentRepo).add(mockPayment);
        Mockito.when(mockedPaymentRepo.getAll()).thenReturn(Arrays.asList(mockPayment));

        pizzaService.addPayment(tableNumber,type,amount);

        assertEquals(pizzaService.getPayments().size(),1);
        assertEquals(pizzaService.getPayments().get(0).getTableNumber(),tableNumber);

        Mockito.verify(mockedPaymentRepo, times(2)).getAll();

    }
}