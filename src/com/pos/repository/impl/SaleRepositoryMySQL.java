package com.pos.repository.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.pos.db.DBConnection;
import com.pos.domain.CashPayment;
import com.pos.domain.QrisPayment;
import com.pos.domain.Sale;
import com.pos.domain.SaleItem;
import com.pos.exception.DBConnectionException;
import com.pos.exception.RepositoryException;
import com.pos.factory.CashierRepositoryFactory;
import com.pos.factory.ItemRepositoryFactory;
import com.pos.repository.CashierRepository;
import com.pos.repository.ItemRepository;
import com.pos.repository.SaleRepository;

public class SaleRepositoryMySQL implements SaleRepository{
	
	@Override
	public void save(Sale sale) throws RepositoryException {
		try (Connection conn = DBConnection.conn()) {
			conn.setAutoCommit(false);
			PreparedStatement statementSale = conn.prepareStatement("INSERT INTO sale (sale_number, transaction_date, cashier) values (?,?,?)");
			statementSale.setString(1, sale.getSaleNumber());
			statementSale.setDate(2, new Date(sale.getTransactionDate().getTime()));;
			statementSale.setString(3, sale.getCashier().getId());
			statementSale.executeUpdate();
			
			for(SaleItem saleItem : sale.getSaleItem()) {
				PreparedStatement statementSaleItem = conn.prepareStatement("INSERT INTO sale_item (item, sale, quantity, price) values (?,?,?,?)");
				statementSaleItem.setString(1, saleItem.getItem().getItemCode());
				statementSaleItem.setString(2, sale.getSaleNumber());
				statementSaleItem.setInt(3, saleItem.getQuantity());
				statementSaleItem.setDouble(4, saleItem.getPrice());
				statementSaleItem.executeUpdate();
			}
			
			PreparedStatement validateSale = conn.prepareStatement("INSERT INTO payment (amount, is_pay, sale) values (?,?,?)");
			validateSale.setDouble(1, sale.totalPrice());
			validateSale.setInt(2, 1);
			validateSale.setString(3, sale.getSaleNumber());
			validateSale.executeUpdate();
			
			if(sale.getPayment() instanceof CashPayment) {
			    PreparedStatement validateCash = conn.prepareStatement("INSERT INTO cash_payment (cash_in_hand, payment) values (?,?)");
			    validateCash.setDouble(1, ((CashPayment) sale.getPayment()).getCashInHand());
			    validateCash.setString(2, sale.getSaleNumber());
			    validateCash.executeUpdate();  
			}
			if (sale.getPayment() instanceof QrisPayment) {
			    PreparedStatement validateQris = conn.prepareStatement("INSERT INTO qris_payment (amount, payment) values (?,?)");
			    validateQris.setDouble(1, (sale.getPayment().getAmount()));
			    validateQris.setString(2, sale.getSaleNumber());
			    validateQris.executeUpdate(); 
			}
			conn.commit();
		}
		catch (SQLException e) {
		    throw new RepositoryException(e.getMessage());
		} catch (DBConnectionException e) {
		    throw new RepositoryException(e.getMessage());
		}

	}

	@Override
	public Sale findByNumber(String number) throws RepositoryException {
		CashierRepository cashierRepo = CashierRepositoryFactory.getCashierRepository();
		ItemRepository itemRepo = ItemRepositoryFactory.getItemRepository();
		
		Sale sale = null;
		try(Connection connection = DBConnection.conn()) {
			PreparedStatement findStatement = connection.prepareStatement("SELECT * FROM sale WHERE sale_number=?");
			findStatement.setString(1, number);
			ResultSet resultSetItem = findStatement.executeQuery();
			while(resultSetItem.next()) {
				sale = new Sale(
						resultSetItem.getString("sale_number"),
						resultSetItem.getDate("transaction_date"),
						cashierRepo.findCashierByID(resultSetItem.getString("cashier")));
			}
			
			PreparedStatement saleItemStatement = connection.prepareStatement("SELECT * FROM sale_item WHERE sale=?");
			saleItemStatement.setString(1, number);
			ResultSet resultSetSaleItem = saleItemStatement.executeQuery();
			List<SaleItem> saleItems = new ArrayList<SaleItem>();
			while(resultSetSaleItem.next()) {
				saleItems.add(new SaleItem(itemRepo.findByItemCode(resultSetSaleItem.getString("item")), resultSetSaleItem.getInt("quantity")));				
			}
			sale.setSaleItems(saleItems);
			return sale;
			
		} catch (SQLException e) {
			throw new RepositoryException(e.getMessage());
		} catch (DBConnectionException e) {
		    throw new RepositoryException(e.getMessage());
		}
	}

}
