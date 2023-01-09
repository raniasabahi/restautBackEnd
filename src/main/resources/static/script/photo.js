$(document)
	.ready(
		function() {

			table = $('#tphoto')
				.DataTable({
					ajax: {
						url: "photo/all",
						dataSrc: ''
					},
					columns: [
						{
							data: "id"
						},
						{
							data: "resto.nom"
						},
						{
							data: "url",
							
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
					url: '/zone/findByIdVille/' + countryId,
					type: 'get',
					success: function(res) {

						$('#zone').html('<option value="">Select Zone</option>');
						$.each(res, function(key, value) {
							$('#zone').append('<option value =' + value.id + '>' + value.nom + '</option>');
						});
					}
				});
			});

			$('#zone').on('change', function() {
				var zoneId = this.value;
				$('#resto').html('');
				$.ajax({
					url: '/resto/byzone/' + zoneId,
					type: 'get',
					success: function(res) {

						$('#resto').html('<option value="">Select Restaurant</option>');
						$.each(res, function(key, value) {
							$('#resto').append('<option value =' + value.id + '>' + value.nom + '</option>');
						});
					}
				});
			});

			$('#btn').click(
				function() {
					var nom = $("#resto");
					var url = $("#url");
					if ($('#btn').text() == 'Ajouter') {
						var p = {
							url: url.val(),
							resto: {
								id: nom.val()
							}

							
						};

						$.ajax({
							url: 'photo/save',
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
							"./page/photo.html");
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
						newLigne += '<h4 class="d-inline-flex">Voulez vous vraiment supprimer cette photo ? </h4>';
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
											url: 'photo/delete/'
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
					;
					var url = $(this).closest('tr').find('td')
						.eq(2).text();
			
					var resto = $(this).closest('tr').find('td')
						.eq(1).text();
					btn.text('Modifier');
					$("#url").val(url);
					
					$("#id").val(id);
					
					var op = $('#resto option').filter(function() { return $(this).html() == resto; }).val();
					$("#resto").val(op);
					
					btn.click(function(e) {
						e.preventDefault();
						var p = {
							id: id,
							url: $("#url").val(),
							
							resto: {
								id: $("#resto").val()

							}
						};
						if ($('#btn').text() == 'Modifier') {
							$.ajax({
								url: 'photo/save',
								contentType: "application/json",
								dataType: "json",
								data: JSON.stringify(p),
								type: 'POST',
								async: false,
								success: function(data,
									textStatus, jqXHR) {
									table.ajax.reload();
									$("#adresse").val('');
									$("#nom").val('');
									btn.text('Ajouter');
								},
								error: function(jqXHR, textStatus,
									errorThrown) {
									console.log(textStatus);
								}
							});
							$("#main-content").load(
								"./page/photo.html");
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
			// url: 'photos/all',
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
			 });