import { useEffect, useState } from "react";
import { Button, Col, Row ,Image, Container} from "react-bootstrap";
import productService from '../services/productServices';
import ProductCarousel from "../componenets/trendingproduct";

const Product=()=>{

    const [product,setProduct]=useState([]);
    useEffect(()=>{
        // const response=productService.getProductDetailsById();
        // if(response.status!=='successs')
        // {

        // }
    },[])

    const Style={
        image:{
            height: 500,
            width: 400,
            borderWidth: '2px',
            borderColor:'lightgrey',
            borderStyle: 'solid'
        },
        borderClass:{
            borderWidth: '2px',
            borderColor:'lightgrey',
            borderStyle: 'solid'
        }
    }
return(
    <Row>
        <Row style={Style.borderClass}>
            <Col style={Style.borderClass}>
            <Container>
            <Image style={Style.image} src="demo.jpg"></Image>
            </Container>
            <ProductCarousel/>
           
            </Col>
            <Col style={Style.borderClass}>
            <Row>
                <Col className="mb-5"><h1>product details</h1></Col>
            </Row>
            <Row><Col style={Style.borderClass} className="mb-5"><h1>Price</h1></Col></Row>
            <Row><Col style={Style.borderClass} className="mb-5"><h1>Lorem ipsum dolor sit amet consectetur adipisicing elit. Perspiciatis quam praesentium autem amet ipsum? Necessitatibus impedit ducimus modi omnis? Quae autem ducimus excepturi, eos quibusdam reprehenderit mollitia consequatur magnam consectetur?</h1></Col></Row>
            <Row><Col><Button style={{width:100}} className="btn btn-dark">add to cart</Button></Col></Row>
            </Col>
        </Row>
        <Row style={{textAlign:'center'}}>
            <h1>Similar Products</h1>
            <ProductCarousel/>
        </Row>
    </Row>
)
}

export default Product;