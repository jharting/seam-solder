/*
 * JBoss, Home of Professional Open Source
 * Copyright 2010, Red Hat, Inc., and individual contributors
 * by the @authors tag. See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,  
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jboss.seam.solder.test.bean.generic.method;

import static org.jboss.seam.solder.test.util.Deployments.baseDeployment;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import javax.inject.Inject;

import org.jboss.arquillian.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
public class QualifierOnlyGenericBeanTest
{
   
   @Deployment
   public static Archive<?> deployment()
   {
      return baseDeployment().addPackage(QualifierOnlyGenericBeanTest.class.getPackage());
   }

   @Inject
   @Foo(1)
   private Kitchen kitchen1;

   @Inject
   @Foo(2)
   private Kitchen kitchen2;

   @Inject
   @Foo(1)
   private Sink sink1;

   @Inject
   @Foo(2)
   private Sink sink2;
   

   @Test
   public void testGeneric()
   {
      
      // Check that producer methods on generic beans are working
      assertNotNull(kitchen1);
      assertEquals("big", kitchen1.getSize());
      assertNotNull(kitchen2);
      assertEquals("small", kitchen2.getSize());
      
      assertNotNull(sink1);
      assertEquals("big", sink1.getKitchen().getSize());
      assertNotNull(sink2);
      assertEquals("small", sink2.getKitchen().getSize());
   }


}
