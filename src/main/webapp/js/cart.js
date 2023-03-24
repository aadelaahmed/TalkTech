var cart = getCartFromLocalStorage() || { items: [], total: 0 };

function addToCart(item) {
  cart.items.push(item);
  cart.total += item.price;
  localStorage.setItem('cart', JSON.stringify(cart));
  renderCart();
}

function removeFromCart(index) {
  const item = cart.items[index];
  cart.items.splice(index, 1);
  cart.total -= item.price;
  localStorage.setItem('cart', JSON.stringify(cart));
  renderCart();
}

function getCartFromLocalStorage() {
  const cartData = localStorage.getItem('cart');
  if (cartData) {
    return JSON.parse(cartData);
  } else {
    return null;
  }
}

function renderCart() {
  const cartElement = document.getElementById('cart');
  cartElement.innerHTML = '';

  cart.items.forEach((item, index) => {
    const li = document.createElement('li');
    const nameSpan = document.createElement('span');
    const priceSpan = document.createElement('span');
    const removeButton = document.createElement('button');

    nameSpan.innerText = item.name;
    priceSpan.innerText = `$${item.price.toFixed(2)}`;
    removeButton.innerText = 'Remove';
    removeButton.addEventListener('click', () => removeFromCart(index));

    li.appendChild(nameSpan);
    li.appendChild(priceSpan);
    li.appendChild(removeButton);
    cartElement.appendChild(li);
  });

  const totalElement = document.createElement('li');
  totalElement.innerText = `Total: $${cart.total.toFixed(2)}`;
  cartElement.appendChild(totalElement);
}

renderCart();