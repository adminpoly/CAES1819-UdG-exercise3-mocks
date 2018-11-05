package org.udg.caes.account;

import mockit.Expectations;
import mockit.Verifications;
import org.junit.jupiter.api.Test;

import mockit.Tested;
import mockit.Mocked;


import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestAccountService {


  @Test
  void testTransfer(@Tested AccountService as, @Mocked AccountManager am)  {

      Account acc1 = new Account("a", 100);
      Account acc2 = new Account("b", 350);

      new Expectations() {{
          am.findAccount("a"); result=acc1;
          am.findAccount("b"); result=acc2;
      }};
      as.setAccountManager(am);
      as.transfer("a", "b", 100);

      assertEquals(0, acc1.getBalance());
      assertEquals(450, acc2.getBalance());

      new Verifications() {{
          am.updateAccount(acc1); times = 1;
          am.updateAccount(acc2); times = 1;
      }};

  }
}