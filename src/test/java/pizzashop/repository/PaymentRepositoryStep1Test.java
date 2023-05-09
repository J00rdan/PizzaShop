package pizzashop.repository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pizzashop.model.Payment;
import pizzashop.model.PaymentType;

import java.io.File;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class PaymentRepositoryStep1Test {

    private PaymentRepository paymentRepository;

    @BeforeEach
    void setUp() {
        try {
            File file = new File("data/test.txt");
            file.createNewFile();
            System.out.println("Created");
        } catch(Exception e) {
            e.printStackTrace();
        }
        paymentRepository = new PaymentRepository("data/test.txt");
    }

    @Test
    public void test1() throws Exception {
        Payment mockPayment = mock(Payment.class);
        when(mockPayment.getTableNumber()).thenReturn(5);
        when(mockPayment.getType()).thenReturn(PaymentType.Card);
        when(mockPayment.getAmount()).thenReturn(20.0);
        paymentRepository.add(mockPayment);
        List<Payment> paymentList = paymentRepository.getAll();
        assertEquals(paymentList.size(),1);

        assertEquals(mockPayment.getAmount(), paymentList.get(0).getAmount());
    }

    @Test
    public void test2()  {
        Payment mockPayment = mock(Payment.class);
        when(mockPayment.getTableNumber()).thenReturn(5);
        when(mockPayment.getType()).thenReturn(PaymentType.Card);
        when(mockPayment.getAmount()).thenReturn(20.0);
        try {
            paymentRepository.add(mockPayment);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        List<Payment> paymentList = paymentRepository.getAll();

        assertEquals(paymentList.size(), 1);
    }

    @AfterEach
    void tearDown() {
        File myObj = new File("data/test.txt");
        if (myObj.delete()) {
            System.out.println("Deleted");
        } else {
            System.out.println("Failed");
        }
    }
}