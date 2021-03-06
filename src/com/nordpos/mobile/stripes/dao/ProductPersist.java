/**
 * Copyright (c) 2012-2013 Nord Trading Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.nordpos.mobile.stripes.dao;

import com.nordpos.mobile.stripes.model.Product;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import net.sf.persist.Persist;

/**
 *
 * @author Andrey Svininykh <svininykh@gmail.com>
 */
public class ProductPersist extends BaseJDBCPersist {

    public ProductPersist(ServletContext servletContext) {
        try {
            persist = new Persist(getConnection(servletContext));
        } catch (Exception ex) {
            Logger.getLogger(ProductPersist.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Product findProductById(String productId) {
        return persist.read(Product.class,
                "SELECT * FROM PRODUCTS WHERE ID = ?",
                productId);
    }

    public Product findProductByRef(String productRef) {
        return persist.read(Product.class,
                "SELECT * FROM PRODUCTS WHERE REFERENCE = ?",
                productRef);
    }

    public List<Product> findProductByCategory(String categoryId) {
        return persist.readList(Product.class,
                "SELECT * FROM PRODUCTS WHERE CATEGORY = ? ORDER BY NAME",
                categoryId);
    }

    public Integer countProductByCategory(String categoryId) {
        return persist.read(Integer.class,
                "SELECT COUNT(ID) FROM PRODUCTS WHERE CATEGORY = ?",
                categoryId);
    }
}
