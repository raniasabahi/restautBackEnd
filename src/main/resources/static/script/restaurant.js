$(document)
	.ready(
		function() {

			table = $('#tresto')
				.DataTable({
					ajax: {
						url: "resto/all",
						dataSrc: ''
					},
					columns: [
						{
							data: "id"
						},
						{
							data: "nom"
						},
						{
							data: "adresse"
						},
						{
							data: "rank"
						},
						{
							data: "etat"
						},
						
						{
							data: "open"
						},
						{
							data: "close"
						},
						{
							data : "serie.nom"
						},
						{
							data : "zone.nom"
						},
						{
							"render": function() {
								return '<button type="button" class="btn btn-outline-danger supprimer">Supprimer</button>';
							}
						},
						{
							"render": function() {
								return '<button type="button" class="btn btn-outline-secondary modifier">Modifier</button>';
							}
						}]
				});
$.ajax({
				url: '/ville/all',
				type: 'GET',
				success: function(data) {
					var option = '';
					data.forEach(e => {
						option += '<option value =' + e.id + '>' + e.nom + '</option>';
					});

					$('#ville').append(option);
				},
				error: function(jqXHR, textStatus,
					errorThrown) {
					console.log(textStatus);
				}

			});

			$('#ville').on('change', function() {
				var countryId = this.value;
				$('#zone').html('');
				$.ajax({
					url: '/zone/findByIdVille/'+countryId,
					type: 'get',
					success: function(res) {

						$('#zone').html('<option value="">Select Zone</option>');
						$.each(res, function(key, value) {
							$('#zone').append('<option value =' + value.id + '>' + value.nom + '</option>');
						});
					}
				});
			});
					$.ajax({
						url:'/serie/all',
						type:'GET',
						success: function(data) {
							var option2 = '';
							data.forEach(e=>{
								option2 += '<option value ='+e.id+'>'+e.nom+'</option>';
							});
							
						$('#serie').append(option2);
						},
						error: function(jqXHR, textStatus,
								errorThrown) {
							console.log(textStatus);
						}
						
					});
					$.ajax({
						url:'/specialite/all',
						type:'GET',
						success: function(data) {
							var option3 = '';
							data.forEach(e=>{
								option3 += '<option value ='+e.id+'>'+e.nom+'</option>';
							});
							
						$('#specialite').append(option3);
						},
						error: function(jqXHR, textStatus,
								errorThrown) {
							console.log(textStatus);
						}
						
					});

			$('#btn').click(
				function() {
					var nom = $("#nom");
					var adresse = $("#adresse");
					var rank = $("#rank");
					
					var lat = $("#lat");
					var log = $("#log");
					var open = $("#open");
					var close = $("#close");
					var weekend = $("#weekend");
					var serie = $("#serie");
					var zone = $("#zone");
					var urlphoto= $("#urlphoto");

					
					if ($('#btn').text() == 'Ajouter') {
						var p = {
							nom: nom.val(),
							adresse: adresse.val(),
							rank: rank.val(),
							etat: 'en cours',
							user:{
								id: 2
							},
							lat: lat.val(),
							log: log.val(),
							urlphoto: urlphoto.val(),
							open: open.val(),
							close: close.val(),
							weekend: weekend.val(),
							serie: {
											id : serie.val()
									},
							zone: {
											id : zone.val()
									}
							
						};

						$.ajax({
							url: 'resto/save',
							contentType: "application/json",
							dataType: "json",
							data: JSON.stringify(p),
							type: 'POST',
							async: false,
							success: function(data, textStatus,
								jqXHR) {
								table.ajax.reload();
							},
							error: function(jqXHR, textStatus,
								errorThrown) {
								console.log(textStatus);
							}
						});
						$("#main-content").load(
							"./page/restaurant.html");
					}
				});

			$('#table-content')
				.on(
					'click',
					'.supprimer',
					function() {

						var id = $(this).closest('tr').find(
							'td').eq(0).text();
						var oldLing = $(this).closest('tr')
							.clone();
						var newLigne = '<tr style="position: relative;" class="bg-light" ><th scope="row">'
							+ id
							+ '</th><td colspan="4" style="height: 100%;">';
						newLigne += '<h4 class="d-inline-flex">Voulez vous vraiment supprimer ce restaurant ? </h4>';
						newLigne += '<button type="button" class="btn btn-outline-primary btn-sm confirmer" style="margin-left: 25px;">Oui</button>';
						newLigne += '<button type="button" class="btn btn-outline-danger btn-sm annuler" style="margin-left: 25px;">Non</button></td></tr>';

						$(this).closest('tr').replaceWith(
							newLigne);
						$('.annuler').click(
							function() {
								$(this).closest('tr')
									.replaceWith(
										oldLing);
							});
						$('.confirmer')
							.click(
								function(e) {
									e.preventDefault();
									$
										.ajax({
											url: 'resto/delete/'
												+ id,
											data: {},
											type: 'DELETE',
											async: false,
											success: function(
												data,
												textStatus,
												jqXHR) {
												if (data
													.includes("error") == true) {
													$(
														"#error")
														.modal();
												} else {
													table.ajax
														.reload();
												}
											},
											error: function(
												jqXHR,
												textStatus,
												errorThrown) {
												$(
													"#error")
													.modal();
											}
										});

								});

					});

			$('#table-content').on(
				'click',
				'.modifier',
				function() {
					var btn = $('#btn');
					var id = $(this).closest('tr').find('td').eq(0)
						.text();
					var nom = $(this).closest('tr').find('td')
						.eq(1).text();
					var adresse = $(this).closest('tr').find('td')
						.eq(2).text();
					var rank = $(this).closest('tr').find('td')
						.eq(3).text();
					var open = $(this).closest('tr').find('td')
						.eq(4).text();
					var close = $(this).closest('tr').find('td')
						.eq(5).text();
					var serie = $(this).closest('tr').find('td')
								.eq(6).text();
					var zone = $(this).closest('tr').find('td')
								.eq(7).text();
							
					btn.text('Modifier');
					$("#nom").val(nom);
					$("#adresse").val(adresse);
					var lat = $("#lat");
					var log = $("#log");
					$("#open").val(open);
					$("#close").val(close);
					var weekend = $("#weekend");
					var op1 = $('#rank option').filter(function () { return $(this).html() == rank; }).val();
					$("#rank").val(op1);
					var op = $('#serie option').filter(function () { return $(this).html() == serie; }).val();
					$("#serie").val(op);
					var op2 = $('#zone option').filter(function () { return $(this).html() == zone; }).val();
					$("#zone").val(op2);
					
					btn.click(function(e) {
						e.preventDefault();
						var p = {
							id: id,
							urlphoto: urlphoto,
							nom: $("#nom").val(),
							adresse: $("#adresse").val(),
							rank: $("#rank").val(),
							lat: lat.val(),
							log: log.val(),
							open: $("#open").val(),
							close: $("#close").val(),
							weekend: weekend.val(),
							serie : {
											id : $("#serie").val()
											
										},
							zone : {
											id : $("#zone").val()
											
										}							
						};
						if ($('#btn').text() == 'Modifier') {
							$.ajax({
								url: 'resto/save',
								contentType: "application/json",
								dataType: "json",
								data: JSON.stringify(p),
								type: 'POST',
								async: false,
								success: function(data,
									textStatus, jqXHR) {
									table.ajax.reload();
									$("#nom").val('');
									$("#adresse").val('');
									$("#rank").val('');
									$("#lat").val('');
									$("#log").val('');
									$("#open").val('');
									$("#close").val('');
									$("#weekend").val('');
									$("#serie").val('');
									$("#zone").val('');
									$("#urlphoto").val('');
									btn.text('Ajouter');
								},
								error: function(jqXHR, textStatus,
									errorThrown) {
									console.log(textStatus);
								}
							});
							$("#main-content").load(
								"./page/restaurant.html");
						}
					});
				});

			// function remplir(data) {
			// var contenu = $('#table-content');
			// var ligne = "";
			// for (i = 0; i < data.length; i++) {
			// ligne += '<tr><th scope="row">' + data[i].id + '</th>';
			// ligne += '<td>' + data[i].code + '</td>';
			// ligne += '<td>' + data[i].nom + '</td>';
			// ligne += '<td>' + data[i].prix + '</td>';
			// ligne += '<td>' + data[i].dateAchat + '</td>';
			// ligne += '<td><button type="button" class="btn
			// btn-outline-danger
			// supprimer">Supprimer</button></td>';
			// ligne += '<td><button type="button" class="btn
			// btn-outline-secondary
			// modifier">Modifier</button></td></tr>';
			// }
			// contenu.html(ligne);
			// }

			// $.ajax({
			// url: 'villes/all',
			// data: {op: ''},
			// type: 'GET',
			// async: false,
			// success: function (data, textStatus, jqXHR) {
			// console.log(data);
			// remplir(data);
			// },
			// error: function (jqXHR, textStatus, errorThrown) {
			// console.log(textStatus);
			// }
			// });
		});