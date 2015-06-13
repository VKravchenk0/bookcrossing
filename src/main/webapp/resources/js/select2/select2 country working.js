
$(document).ready(function() {

					var countrySelect = $(".country-select");
					var citySelect = $(".city-select");
					var countryId = $("#country-hidden").val();
					var cityId = $("#city-hidden").val();
					console.log(cityName);
					
					
					$('#myModal').on('show.bs.modal', function () {
						
						console.log("country id: " + countryId);
						if (countryId > 0) {
							countrySelect.val(countryId).trigger("change");
							citySelect.prop("disabled", false);
						}
						if (countryId > 0 && cityId > 0) {
							console.log("trying to change city");
							citySelect.select2('data', {id:314, text:'Киев'});
						}
						
					      //alert('Hey, I heard you like modals...');
					});
					
					$(".country-select").select2({
						placeholder : "Выберите страну",
						data : countries,
						multiple : false
					});

					$(".country-select").on("select2:select", function(e) {
						if (countryId != e.params.data["id"]) {
							clearCities();
						}
						countryId = e.params.data["id"];
						$("#country-hidden").val(countryId);
						
						if (countryId > 0) {
							$(".city-select").prop("disabled", false);
						} else {
							$(".city-select").prop("disabled", true);
						}

					});
					
					$(".somediv").click(function() {
						country = $(".country-select").val();
						city = $(".city-select").val()
						console.log("country id : " + country);
						console.log("city id : " + city);
						if (country == '') {
							console.log("country is empty string");
						}
						
						//$(".country-select").val(1).trigger("change");
					});
					
					
					
					
					
					$(".city-select").select2({
										
										placeholder : "Выберите город",
										disabled : true,
										allowClear : true,
										ajax : {
											url : function() {return "https://api.vk.com/method/database.getCities?country_id=" + countryId + "&lang=ru";},
											dataType : 'jsonp',
											delay : 250,
											data : function(params) {
												return {
													q: params.term,
													page : params.page
												};
											},
											processResults : function(data,	page) {
												return {
													results : $.map(data.response,
																	function(item) {
																		area = (item.area === undefined ? '' : (item.area));
																		region = (item.region === undefined ? '' : (item.region));
																		regionArea = '';
																		if (area != '' || region != '') {
																			regionArea = regionArea.concat("<p class='regionArea'>");
																		}
																		if (area != '') {
																			regionArea = regionArea.concat(area);
																		}
																		if (area != '' && region != '') {
																			regionArea = regionArea.concat("<br/>");
																		}
																		if (region != '') {
																			regionArea = regionArea.concat(region);
																		}
																		
																		if (area != '' || region != '') {
																			regionArea = regionArea.concat("</p>");
																		}
																		return {
																			text : item.title + regionArea,
																			id : item.cid,
																			title : item.title,
																			area : area,
																			region : region
																		}
																	})
												};

											},
											cache : true
										},
										escapeMarkup : function(markup) {
											return markup;
										},
								        templateSelection: formatDataSelection
									});

					function formatDataSelection (data) {
						return data.title;
					}
					
					function clearCities() {
						$(".city-select").val(null).trigger("change");
					}
					
					
					
});