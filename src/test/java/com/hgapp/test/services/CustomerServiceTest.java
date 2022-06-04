/*
 * package com.hgapp.test.services;
 * 
 * import static org.junit.Assert.assertTrue;
 * 
 * import org.junit.Before; import org.junit.Test; import
 * org.junit.runner.RunWith; import org.mockito.InjectMocks; import
 * org.mockito.Mock; import org.mockito.MockitoAnnotations; import
 * org.mockito.junit.MockitoJUnitRunner;
 * 
 * import com.hgapp.service.CustomerService; import
 * com.hgapp.service.impl.ServiceManagerImpl;
 * 
 * @RunWith(MockitoJUnitRunner.class) public class CustomerServiceTest {
 * 
 * @Mock private CustomerService custService;
 * 
 * @InjectMocks
 * 
 * ServiceManagerImpl servicemanger;
 * 
 * @Before public void setup() { MockitoAnnotations.initMocks(this); }
 * 
 * @Test public void findAll() { int size =
 * servicemanger.getCustomerService().findAllCustomers().size();
 * 
 * assertTrue(size > 0); }
 * 
 * }
 */