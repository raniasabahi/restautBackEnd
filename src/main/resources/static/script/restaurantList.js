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
							data: "user.username"
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
						
						]
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


		});