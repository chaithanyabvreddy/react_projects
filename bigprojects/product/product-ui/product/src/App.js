import React from 'react';
import axios from 'axios';

class App extends React.Component{
	constructor(props){
		super(props);
		this.state={products:[],name:'',price:'',noOfProducts:'',productColor:'',id:0};
		this.getAllProduct()
	}
	getAllProduct= async () =>{
		const resp = await axios.get(`http://localhost:8080/getAllProduct`);
		console.log(resp.data)
		this.setState({products:resp.data});
	}
	
	deleteProduct = async (productid) => {
		await	axios.post('http://localhost:8080/deleteProduct?id='+productid);
		this.getAllProduct()
	}
	
	updateProduct = async (product) => {
		this.setState({name:product.name});
		this.setState({price:product.price});
		this.setState({noOfProducts:product.noOfProducts});
		this.setState({productColor:product.productColor});
		this.setState({id:product.id});
		
	}
	
	updateproductToDb=async ()=>{
		const resp = await axios.post(`http://localhost:8080/createProduct`,{name:this.state.name,
																			 price:this.state.price,
																			 noOfProducts:this.state.noOfProducts,
																			 productColor:this.state.productColor,
																			 id:this.state.id});

			console.log(resp.data);
			this.getAllProduct();

	}
	
	addProduct= async () =>{
		const resp = await axios.post(`http://localhost:8080/createProduct`,{name:this.state.name,
																			 price:this.state.price,
																			 noOfProducts:this.state.noOfProducts,
																			 productColor:this.state.productColor});

			console.log(resp.data);
			this.getAllProduct();
	}
	
	
	
	
	render(){
	  return (
		<div>
		Name: <input type="text"   value={this.state.name}  onChange={event=>this.setState({name:event.target.value})} /><br/>
		price: <input type="text"   value={this.state.price}  onChange={event=>this.setState({price:event.target.value})} /><br/>
		noOfProducts: <input type="text"   value={this.state.noOfProducts}  onChange={event=>this.setState({noOfProducts:event.target.value})} /><br/>
		productColor: <input type="text"   value={this.state.productColor}  onChange={event=>this.setState({productColor:event.target.value})} /><br/>
		<input type="button" onClick={this.addProduct}  value="Add Product"/>
		<input type="button" onClick={this.updateproductToDb}  value="update Product"/>
		
		<table border="1">
				<tr>
				<th>name</th>
				<th>price</th>
				<th>noOfProducts</th>
				<th>productColor</th>
				<th>delete</th>
				<th>update</th>
				</tr>
		  {this.state.products.map(
		  
			function(product){
				return(
					<tr>
					
					<td>{product.name}</td>
					<td>{product.price}</td>
					<td>{product.noOfProducts}</td>
					<td>{product.productColor}</td>
					<td><button onClick={()=>this.deleteProduct(product.id)} >delete</button></td>
					<td><button onClick={()=>this.updateProduct(product)} >update</button></td>
					</tr>
				)},this
				
				
		   )}
			</table>

		</div>
	  );
}

}

export default App;


	